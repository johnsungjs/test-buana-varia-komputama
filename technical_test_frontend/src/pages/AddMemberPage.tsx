import { Paper } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddMemberPage() {
  const navigation = useNavigate();

  const [name, setName] = useState<string>("");
  const [position, setPosition] = useState<string>("");

  return (
    <Paper sx={{ m: 2, p: 2 }}>
      <div>
        <div>Name :</div>
        <input
          className="p-1 bg-transparent border-2 border-slate-400 rounded-md"
          placeholder="Insert Name ..."
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div className="pt-2">
        <div>Position :</div>
        <input
          className="p-1 bg-transparent border-2 border-slate-400 rounded-md"
          placeholder="Insert Position ..."
          value={position}
          onChange={(e) => setPosition(e.target.value)}
        />
      </div>
      <div className="pt-2">
        <div>Reports To :</div>
        <input
          className="p-1 bg-transparent border-2 border-slate-400 rounded-md"
          placeholder="Insert Position ..."
          value={position}
          onChange={(e) => setPosition(e.target.value)}
        />
      </div>
      <div className="pt-4">
        <button
          className="bg-primary px-4 py-1 rounded-md text-white"
          onClick={() => navigation("/member/add")}
        >
          Submit Form
        </button>
      </div>
    </Paper>
  );
}
