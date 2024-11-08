import { useParams } from 'react-router-dom'

export default function MemberDetailPage() {
  const param = useParams();
  console.log('param', param)
  return (
    <div>MemberDetailPage</div>
  )
}
