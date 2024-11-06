interface LoaderProps {
  message: string
}

export default function LoaderMini({ message = "" }: LoaderProps) {
  return (
    <div className="mt-4 h-[20px] flex flex-col gap-y-0 items-center justify-center">
      <div className="w-7 h-7 relative animate-spin">
        <div>load</div>
        {/* <img alt="robot" src="/assets/svgs/robot.svg" /> */}
      </div>
      <p className="text-xs">{message}</p>
    </div>
  );
}
