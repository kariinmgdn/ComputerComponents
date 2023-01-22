import { changeStatus } from "@/lib";
import { useState } from "react";
import React from "react";
import { Button, Form, Input, Select } from "antd";
import { RefreshData } from "./RefreshData";

interface Option {
  value: string;
  label: string;
}

const { Option } = Select;

const SelectStatusForm = () => {
  const refreshData = RefreshData();

  const options = ["Izveidots", "Apstiprināts", "Noraidīts"];

  const [id, setId] = useState("");
  const [status, setStatus] = useState("");
  return (
    <>
      <h2 className="title2">Statusa atjaunošana:</h2>
      <Form.Item name="id">
        <label>
          Pieprasījuma ID:
        </label>
        <Input
          value={id}
          onChange={(e) => setId(e.target.value)}
          placeholder="Ievadiet pieprasījuma ID..."
        />
      </Form.Item>
      <Form.Item name="status">
        <label>
          Statuss:
        </label>
        <Select
          value={status}
          onChange={(value) => setStatus(value)}
        >
          {options.map((component: string) => (
            <Option key={component} value={component}>
              {component}
            </Option>
          ))}
        </Select>
      </Form.Item>
      <Button
      className="btn"
        onClick={async () => {
          const response = await changeStatus(id, status);
          if (response.status < 300) {
            alert(`Statuss tika atjaunots pieprasījumam ar ID: ${id}`);
            setId("");
            setStatus("");
            refreshData();
            
          }
        }}
      >
        Saglabāt
      </Button>
    </>
  );
};

export default SelectStatusForm;
