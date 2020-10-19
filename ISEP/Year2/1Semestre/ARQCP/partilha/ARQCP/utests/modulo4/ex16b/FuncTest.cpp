#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h> 
#include <string.h> 
#include "asm.h"   
}

//START: testGroup
TEST_GROUP(changes)
{
    int  expected;  

    void setup()
    {
        expected =0;  
    }
    void teardown()
    {
    }
    void expect(int num )
    {
        expected = num; 
    }
    void given(int x )
    { 
	int result=x; 
	changes(&result); 
        LONGS_EQUAL(expected, result);
    }
};
//END: testGroup

TEST(changes, Zero)
{
    expect(0);	  
    given(0);	
}

TEST(changes, One)
{
    expect(0xfffff0ff);	  
    given(-1);	
}
TEST(changes, Two)
{
    expect(0xff);	  
    given(0xff);	
}
TEST(changes, Three)
{
    expect(0xfffff0ff);	  
    given(0xfffff0ff);	
}


TEST(changes, Four)
{
    expect(0xfffff7ff);	  
    given(0xfffff8ff);	
}



TEST(changes, Five)
{
    expect(0xfffff7ff);	  
    given(0xfffff7ff);	
} 


TEST(changes, Six)
{
    expect(0x00000700);	  
    given(0x00000700);	
} 



TEST(changes, Seven)
{
    expect(0x00000700);	  
    given(0x00000800);	
} 


