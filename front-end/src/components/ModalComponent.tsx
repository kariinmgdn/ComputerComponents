import React, { useState } from "react";
import { Button, Modal } from "antd";

export function ModalComponent(props: { name: string; input: JSX.Element }) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <Button className="modal-btn" type="primary" onClick={showModal}>
        {props.name}
      </Button>
      <Modal
        open={isModalOpen}
        onCancel={handleCancel}
        footer={
          <Button key="back" onClick={handleCancel}>
            Iziet
          </Button>
        }
      >
        {props.input}
      </Modal>
    </>
  );
}
