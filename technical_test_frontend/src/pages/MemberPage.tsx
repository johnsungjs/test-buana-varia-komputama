import TableMember from "../components/TableMember";

const dataDummy = [
  {
    name: 'Adi',
    position: 'Manager'
  },
  {
    name: 'Budi',
    position: 'Manager'
  },
  {
    name: 'Coki',
    position: 'Manager'
  },
  {
    name: 'Dimas',
    position: 'Manager'
  },
  {
    name: 'Entok',
    position: 'Manager'
  },
  {
    name: 'Frans',
    position: 'Manager'
  },
];

export default function MemberPage() {
  
  return (
    <div>
      <div className="px-4">
        <div className="text-xl">Search Member: </div>
        <TableMember data={dataDummy}/>
      </div>
    </div>
  );
}
