#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(count_odd)
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
    void given(short  p[] ,  int  num )
    { 
	int result; 
	result=count_odd((short*)p,num); 
        LONGS_EQUAL(expected, result);
    }
};
//END: testGroup

TEST(count_odd, Zero)
{
    expect(0); 
    short v[]={}; 
    given(v,0);
}



TEST(count_odd, One)
{
    expect(1); 
    short v[]={1}; 
    given(v,1);
}  


TEST(count_odd, OneA)
{
    expect(0); 
    short v[]={0}; 
    given(v,1);
}  


TEST(count_odd, OneB)
{
    expect(1); 
    short v[]={-1}; 
    given(v,1);
} 


TEST(count_odd,Three) 
{
    expect(2); 
    short v[]={1,2,3}; 
    given(v,3);
} 


TEST(count_odd,ThreeB) 
{
    expect(3); 
    short v[]={1,2,3,4,5,6}; 
    given(v,6);
}  
