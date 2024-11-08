const baseUrl = "http://localhost:8080";

export const findAllPosition = `${baseUrl}/api/position/find-all`;
export const findOnePosition = (id: string) =>
  `/api/position/find-one?id=${id}`;
export const addOnePosition = `${baseUrl}/api/position/add-one`;

export const findAllMember = `${baseUrl}/api/member/add-one`;
export const findOneMember = (credential: string) =>
  `${baseUrl}/api/member/find-one-by-credential?credential=${credential}`;
