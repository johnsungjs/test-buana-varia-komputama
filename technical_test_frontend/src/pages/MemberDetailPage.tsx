import { Paper } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";

export default function MemberDetailPage() {
  const navigation = useNavigate();

  return (
    <Paper sx={{ m: 2, p: 2 }}>
      <div>
        <div>Name :</div>
        <div className="text-primary font-bold">John</div>
      </div>
      <div className="pt-2">
        <div>Position :</div>
        <div className="text-primary font-bold">Manajer</div>
      </div>
      <div className="pt-2">
        <div>Reports To :</div>
        <div className="text-primary font-bold">Boss</div>
      </div>
      <div className="pt-4">
        <button
          className="bg-primary px-4 py-1 rounded-md text-white"
          onClick={() => navigation(-1)}
        >
          Back
        </button>
      </div>
    </Paper>
  );
}
