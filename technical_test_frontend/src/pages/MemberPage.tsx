import { useState } from "react";
import TableMember from "../components/TableMember";

const dataDummy = [
  {
    name: "Adi",
    position: "Manager",
  },
  {
    name: "Budi",
    position: "Manager",
  },
  {
    name: "Coki",
    position: "Manager",
  },
  {
    name: "Dimas",
    position: "Manager",
  },
  {
    name: "Entok",
    position: "Manager",
  },
  {
    name: "Frans",
    position: "Manager",
  },
];

export default function MemberPage() {
  const [searchInput, setSearchInput] = useState<string>("");

  const filteredData = dataDummy.filter((e) =>
    e.name.toLowerCase().includes(searchInput.toLowerCase())
  );

  return (
    <div>
      <div className="px-4">
        <div className="flex gap-2 mb-4">
          <input
            className="p-1 bg-transparent border-2 border-slate-400 rounded-md"
            placeholder="Search Member Here"
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
          />
          <button className="bg-primary px-4 py-1 rounded-md text-white">+ New Member</button>
        </div>
        <TableMember data={filteredData} />
      </div>
    </div>
  );
}
