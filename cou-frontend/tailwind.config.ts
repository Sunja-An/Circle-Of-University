import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/shared/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/widgets/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    screens: {
      xl: { max: "1200px" },
      lg: { max: "1074px" },
      md: { max: "768px" },
      sm: { max: "640px" },
    },
    extend: {
      fontFamily: {
        studioSans: ["studioSans"],
        pretendard: ["pretendard-JP"],
      },
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        grayground: "var(--gray-background)",
        primary: "var(--primary)",
        secondary: "var(--secondary)",
        pink_0: "var(--pink-0)",
        pink_1: "var(--pink-1)",
        pink_2: "var(--pink-2)",
        pink_3: "var(--pink-3)",
        pink_4: "var(--pink-4)",
        pink_5: "var(--pink-5)",
        gray_0: "var(--gray-0)",
        gray_1: "var(--gray-1)",
        gray_2: "var(--gray-2)",
        gray_3: "var(--gray-3)",
        gray_4: "var(--gray-4)",
        gray_5: "var(--gray-5)",
        orange_0: "var(--orange)",
        orange_1: "var(--orange-1)",
      },
      backgroundImage: {
        linearcolor: "linear-gradient(to bottom, #FFFFFF 66%, #FE9292)",
        reverselinear: "linear-gradient(to top, #FFFFFF 66%, #FE9292)",
      },
    },
  },
  plugins: [],
} satisfies Config;
