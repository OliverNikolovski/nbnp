export interface EnrolledSubject {
  studentId: number;
  subjectId: number;
  semesterId: number;
  professorId: number;
  code: string;
  credits: number;
  subject: string;
  semesterNumber: number;
  program: string;
  studyType: string;
  profFirstName: string;
  profLastName: string;
  semesterType: string;
  semesterStartDate: Date;
  semesterEndDate: Date;
}
