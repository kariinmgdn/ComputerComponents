import { getComponentList } from "@/lib";
import { InputForm } from "@/components/Form";
import { formatData } from "@/lib/FormatData";
import SelectStatusForm from "@/components/SelectStatus";
import ComponentTable from "@/components/Table";
import { ModalComponent } from "@/components/ModalComponent";

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

export default function Home(props: Props) {
  return (
    <>
      <div className="component-section">
        <h2 className="title">Datortehnikas pieprasīšanas sistēma</h2>
        <div className="component-container">
          <div className="input-conatiner">
            <div className="item">
              {ModalComponent("Izveidot pieprasījumu", InputForm())}
              {ModalComponent("Mainīt statusu", SelectStatusForm())}
            </div>
          </div>
          <div>
            <div>{ComponentTable(props.components)}</div>
          </div>
        </div>
      </div>
    </>
  );
}
