import axios from "axios";

export const addComponent = async (
  name: string,
  parameters: string,
  reason: string
) => {
  return await axios({
    method: "post",
    url: "http://localhost:3000/addComponent/",
    data: {
      name: name.trim(),
      parameters: parameters.trim(),
      reason: reason.trim(),
    },
  }).catch((e) => {
    alert("Visiem laukiem ir jābūt aizpildītiem!");
    return e.response;
  });
};

export const getComponentList = async () => {
  return await axios("http://localhost:8080/components/", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
};

export const changeStatus = async (id: string, status: string) => {
  id = id.trim();
  return axios({
    method: "PUT",
    url: `http://localhost:3000/changeStatus/${id}`,
    headers: {
      "Content-Length": 0,
      "Content-Type": "text/plain",
    },
    responseType: "text",
    data: status,
  }).catch((e) => {
    if (e.response.status > 400) {
      alert("Neeksistējošs ID numurs!");
    } else {
      alert("Nederīgs statuss!");
    }
    return e.response;
  });
};

export const deleteComponent = async (id: number) => {
  return await fetch(`http://localhost:3000/deleteComponent/${id}`, {
    method: "DELETE",
  });
};
