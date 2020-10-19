#include <CppUTest/TestHarness.h>

extern "C"
{
#include <stdio.h>
#include <memory.h>

#include <string.h>
  
 #include "asm.h"
 char * ptr1;
 char * ptr2; 
 
} 

//START: testGroup
TEST_GROUP(str_copy_p)
{
    char output[100];
    const char * expected;
    void setup()
    {
        memset(output, 0xaa, sizeof output);
        expected = "";
        ptr2=output;          // 
    }
    void teardown()
    {
    }
    void expect(const char * s)
    {
        expected = s;
		

	}
    void given(const char * str)
    {
    	ptr1=(char*) str; 	
    	str_copy_p(); 	
        STRCMP_EQUAL(expected, output);
        BYTES_EQUAL(0xaa, output[strlen(expected) + 1]);
    }
};
//END: testGroup


TEST(str_copy_p, Hey)
{
    expect("hey");
    given("hey");
}

TEST(str_copy_p, Hello)
{
    expect("Hello World\n");
    given("Hello World\n");
}

TEST(str_copy_p, One_b)
{
    expect("One 8");
    given("One 0");
}



TEST(str_copy_p, Two_b)
{
    expect("Two 88");
    given("Two 00");
}


TEST(str_copy_p, Two_B)
{
    expect("Two BB");
    given("Two BB");
}


TEST(str_copy_p, Two_v)
{
    expect("Two vv");
    given("Two vv");
}

TEST(str_copy_p, Null_String)
{
    expect("");
    given("");
}





//END: RefactoredTests


