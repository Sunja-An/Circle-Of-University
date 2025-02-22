"use client";

import { motion } from "motion/react";
import { textAnimation } from "../../constants";
import { cn } from "@/shared/utils";

function AnimationTexture({
  content,
  className,
}: {
  content: string;
  className?: string;
}) {
  return (
    <motion.span
      initial={textAnimation.initial}
      animate={textAnimation.animate}
      exit={textAnimation.exit}
      className={cn("font-pretendard text-black", className)}
    >
      {content}
    </motion.span>
  );
}

export { AnimationTexture };
