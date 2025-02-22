"use client";

import React from "react";

function LanguageChanger() {
  return (
    <div className="w-full flex justify-center items-center gap-2">
      <span className="font-light text-sm text-black">JPN</span>
      <div className="w-[1px] h-[2vh] bg-gray-400 rounded-full" />
      <span className="font-light text-sm text-gray-400 hover:text-gray-600 duration-300">
        ENG
      </span>
    </div>
  );
}

export { LanguageChanger };
