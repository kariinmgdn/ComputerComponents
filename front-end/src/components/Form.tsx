import { addComponent } from "@/lib";
import { Button, Form, Input, Typography } from "antd";
import { useRouter } from "next/router";
import React, { useState } from "react";

const { Title } = Typography;

export function InputForm() {
  const router = useRouter();
  const refreshData = () => router.replace(router.asPath);

  const [name, setName] = useState("");
  const [parameters, setParameters] = useState("");
  const [reason, setReason] = useState("");

  const handleSubmit = (evt: { preventDefault: () => void }) => {
    evt.preventDefault();
  };

  return (
    <>
      <Title level={4}>Jauns Pieprasījums:</Title>
      <form onSubmit={handleSubmit}>
        <Form.Item name="name">
          <label>
            Nosaukums:
            <Input
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Ievadiet nosaukumu..."
            />
          </label>
        </Form.Item>
        <Form.Item name="parameters">
          <label>
            Parametri:
            <Input
              value={parameters}
              onChange={(e) => setParameters(e.target.value)}
              placeholder="Ievadiet parametrus..."
            />
          </label>
        </Form.Item>
        <Form.Item name="reason">
          <label>Iemesls:</label>
          <Input
            value={reason}
            onChange={(e) => setReason(e.target.value)}
            placeholder="Ievadiet iemeslu..."
          />
        </Form.Item>
        <Button
          onClick={async () => {
            const response = await addComponent(name, parameters, reason);
            if (response.status < 300) {
              alert("Jauns pieprasījums ir izveidots!");
              setName("");
              setParameters("");
              setReason("");
              refreshData();
            }
          }}
        >
          Izveidot
        </Button>
      </form>
    </>
  );
}
