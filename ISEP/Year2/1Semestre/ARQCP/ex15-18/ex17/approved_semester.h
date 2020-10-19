#ifndef APPROVED_SEMESTER_H
#define APPROVED_SEMESTER_H
typedef struct{
int n_students;
unsigned char *individual_grades;
}group;
int approved_semester(group *g);
#endif
