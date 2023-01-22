import { useRouter } from "next/router";

export function RefreshData() {
    const router = useRouter();

    const refreshData = () => {
      router.replace(router.asPath);
    };

    return refreshData;
}