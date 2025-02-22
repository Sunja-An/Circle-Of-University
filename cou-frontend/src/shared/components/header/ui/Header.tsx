"use client";

import Image from "next/image";

import { IMG_LOGO } from "@/shared/constants";
import { HeaderContent } from "@/shared/components/header/constants";
import { LanguageChanger } from "@/shared/components/button";

function Header() {
  return (
    <header className="fixed px-16 py-6 w-full h-16 flex">
      <div className="w-1/3 flex justify-center items-center">
        <div className="w-fit h-fit flex justify-center items-center gap-2 cursor-pointer">
          <Image
            src={IMG_LOGO}
            alt="logo"
            width={28}
            height={28}
            className="object-contain"
          />
          <span className="font-bold text-base">COU</span>
        </div>
      </div>
      <nav className="w-1/2 flex justify-end items-center">
        <ul className="w-full flex justify-end gap-4">
          {HeaderContent.map((item, key) => {
            return (
              <li
                key={key}
                className="px-4 py-2 font-semibold text-sm text-black rounded-lg hover:bg-gray-200 duration-300"
              >
                {item.title}
              </li>
            );
          })}
        </ul>
      </nav>
      <div className="w-1/6 flex justify-start items-center">
        <LanguageChanger />
      </div>
    </header>
  );
}

export { Header };
