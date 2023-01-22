import React from "react";
import { Table, Divider, Button } from "antd";
import type { ColumnsType } from "antd/es/table";
import { deleteComponent } from "@/lib";
import { RefreshData } from "./RefreshData";
import SelectStatusForm from "./SelectStatus";

interface DataType {
  id: number;
  name: string;
  parameters: string;
  reason: string;
  status: string;
  time: Date;
}

export function DeleteButton(id: number) {
  const refreshData = RefreshData();
  return (
    <Button
      className="btn"
      onClick={async () => {
        const conf = confirm(`Vai tiešām vēlaties dzēst pieprasījumu?`);
        if (conf) {
          const response = await deleteComponent(id);
          if (response.status < 300) {
            refreshData();
          }
        }
      }}
    >
      Dzēst
    </Button>
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
    render: (record: { id: number }) => DeleteButton(record.id),
  },
];

const ComponentTable = (data: DataType[]) => (
  <>
    <Divider>Visi Pieprasījumi</Divider>
    <Table columns={columns} dataSource={data} size="middle" />
  </>
);

export default ComponentTable;
