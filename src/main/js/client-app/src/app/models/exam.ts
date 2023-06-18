export interface Exam {
  examId: number;
  studentId: number;
  studentIndeks: string;
  code: string;
  subject: string;
  subjectId: number;
  session: string;
  date: Date;
  semester: number;
  credits: number;
  winterOrSummer: string;
  grade: number;
  professorId: number;
  profFirstName: string;
  profLastName: string;
}
