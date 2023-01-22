/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
}

module.exports = () => {
  const rewrites = () => {
    return [
      {
        source: "/addComponent",
        destination: "http://localhost:8080/components/",
      },
      {
        source: "/changeStatus/:path",
        destination: "http://localhost:8080/components/:path",
      },
      {
        source: "/deleteComponent/:path",
        destination: "http://localhost:8080/components/:path",
      },
    ];
  };
  return {
    rewrites,
  };
};
