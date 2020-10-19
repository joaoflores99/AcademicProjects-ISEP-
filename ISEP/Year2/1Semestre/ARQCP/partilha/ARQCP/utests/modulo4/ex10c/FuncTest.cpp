#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(vec_count_bits_zero)
{
    int  expected;
    void setup()
    {
        expected = 0; 
    }
    void teardown()
    {
    }
    void expect(int num )
    {
        expected = num;
    }
    void given(int  p[] ,  int  num )
    { 
	int result; 
	result=vec_count_bits_zero((int*)p,num); 
        LONGS_EQUAL(expected, result);
    }
};
//END: testGroup

TEST(vec_count_bits_zero, Zero)
{
    expect(0); 
    int v[]={}; 
    given(v,0);
}



TEST(vec_count_bits_zero, One)
{
    expect(31); 
    int v[]={1}; 
    given(v,1);
}  


TEST(vec_count_bits_zero, ZeroA)
{
    expect(32); 
    int v[]={0}; 
    given(v,1);
}  


TEST(vec_count_bits_zero, MinusOne)
{
    expect(0); 
    int v[]={-1}; 
    given(v,1);
} 


TEST(vec_count_bits_zero,Three) 
{
    expect(3); 
    int v[]={-1,-2,-4}; 
    given(v,3);
} 


TEST(vec_count_bits_zero,ThreeB) 
{
    expect(32); 
    int v[]={-1,-1,0,-1,-1,-1}; 
    given(v,6);
}  
