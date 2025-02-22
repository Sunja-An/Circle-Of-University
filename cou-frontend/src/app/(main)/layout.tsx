import { COULayout } from "@/shared/layout";
import { type ReactNode } from "react";

export default function MainLayout({ children }: { children: ReactNode }) {
  return <COULayout>{children}</COULayout>;
}
