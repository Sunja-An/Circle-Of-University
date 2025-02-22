import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "COU",
  description: "Circle Of University",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`scroll-smooth antialiased`}>{children}</body>
    </html>
  );
}
