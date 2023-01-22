import React from "react";
import { Table, Divider, Button, Popconfirm } from "antd";
import type { ColumnsType } from "antd/es/table";
import { deleteComponent } from "@/lib";
import { useRouter } from "next/router";

interface DataType {
  id: number;
  name: string;
  parameters: string;
  reason: string;
  status: string;
  time: Date;
}

export function DeleteButton(props: { id: number }) {
  const router = useRouter();

  const refreshData = () => router.replace(router.asPath);

  return (
    <>
      <Popconfirm
        title="Pieprasījuma dzēšana"
        description="Vai tiešām vēlaties izdzēst šo pieprasījumu?"
        onConfirm={async () => {
          const response = await deleteComponent(props.id);
          if (response.status < 300) {
            refreshData();
          }
        }}
        okText="Jā"
        cancelText="Nē"
      >
        <Button href="#">Dzēst</Button>
      </Popconfirm>
    </>
  );
}

const columns: ColumnsType<DataType> = [
  {
    title: "ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Nosaukums",
    dataIndex: "name",
  },
  {
    title: "Parametri",
    dataIndex: "parameters",
  },
  {
    title: "Iemesls",
    dataIndex: "reason",
  },
  {
    title: "Statuss",
    dataIndex: "status",
  },
  {
    title: "Laiks",
    dataIndex: "time",
  },
  {
    title: "Dzēst",
    key: "operation",
    fixed: "right",
    width: 100,
    render: (record: { id: number }) => <DeleteButton id={record.id} />,
  },
];

const ComponentTable = (props: { data: DataType[] }) => (
  <>
    <Divider>Visi Pieprasījumi</Divider>
    <Table columns={columns} dataSource={props.data} size="middle" />
  </>
);

export default ComponentTable;
