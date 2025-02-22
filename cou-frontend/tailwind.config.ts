import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
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
      },
      backgroundImage: {
        linearcolor: "linear-gradient(to bottom, #FFFFFF 66%, #FE9292)",
      },
    },
  },
  plugins: [],
} satisfies Config;
