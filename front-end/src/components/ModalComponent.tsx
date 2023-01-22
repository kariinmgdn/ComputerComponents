import React, { useState } from 'react';
import { Button, Modal } from 'antd';

export function ModalComponent(name: string, input: JSX.Element) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <Button className="modal-btn" type="primary" onClick={showModal}>
        {name}
      </Button>
      <Modal open={isModalOpen} onCancel={handleCancel} footer={
          <Button className='btn' key="back" onClick={handleCancel}>
            Iziet
          </Button>}>
        {input}
        
      </Modal>
    </>
  );
};