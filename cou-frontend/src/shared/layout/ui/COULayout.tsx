import { type ReactNode } from "react";

import { Header } from "@/shared/components/header";

function COULayout({ children }: { children: ReactNode }) {
  return (
    <main className="w-screen min-h-screen">
      <Header />
      <article className="w-full h-screen bg-linearcolor">{children}</article>
      <section className="w-full"></section>
      <section className=""></section>
      <section className=""></section>
      <section className=""></section>
      <section className=""></section>
    </main>
  );
}

export { COULayout };
