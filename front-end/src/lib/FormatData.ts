export const formatData = (
  data: {
    id: number;
    name: string;
    parameters: string;
    reason: string;
    status: string;
    time: string;
  }[]
) => {
  return data.map((value) => {
    return {
      id: value.id,
      name: value.name,
      parameters: value.parameters,
      reason: value.reason,
      status: value.status
        .replaceAll("CREATED", "Izveidots")
        .replaceAll("APPROVED", "Apstiprināts")
        .replaceAll("REJECTED", "Noraidīts"),
      time: value.time,
    };
  });
};
