import { AnimationTexture } from "@/shared/components/animation";
import React from "react";

function Mainpage() {
  return (
    <div className="w-full flex flex-col justify-center items-center gap-8">
      <AnimationTexture
        content="サークルを簡単に"
        className="font-bold text-5xl"
      />
      <AnimationTexture
        content="管理してみましょう"
        className="font-normal text-3xl"
      />
    </div>
  );
}

export { Mainpage };
