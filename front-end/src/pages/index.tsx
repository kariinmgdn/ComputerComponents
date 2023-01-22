import { getComponentList } from "@/lib";
import { InputForm } from "@/components/Form";
import { formatData } from "@/lib/FormatData";
import SelectStatusForm from "@/components/SelectStatus";
import ComponentTable from "@/components/Table";
import { ModalComponent } from "@/components/ModalComponent";
import { Typography } from "antd";

export async function getStaticProps() {
  const components = await getComponentList();

  return {
    props: {
      components: formatData(components.data),
    },
  };
}

interface Props {
  components: {
    id: number;
    name: string;
    parameters: string;
    reason: string;
    status: string;
    time: Date;
  }[];
}

const { Title } = Typography;

export default function Home(props: Props) {
  return (
    <>
      <div className="component-section">
        <Title className="title">Datortehnikas pieprasīšanas sistēma</Title>
        <div className="component-container">
          <div className="input-conatiner">
            <div className="item">
              <ModalComponent
                name={"Izveidot pieprasījumu"}
                input={<InputForm />}
              />
              <ModalComponent
                name={"Mainīt statusu"}
                input={<SelectStatusForm />}
              />
            </div>
          </div>
          <div>
            <div>
              <ComponentTable data={props.components} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
