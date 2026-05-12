const hre = require("hardhat");

const main = async () => {
    const contractFactory = await hre.ethers.getContractFactory("Demo");
    const demoContract = await contractFactory.deploy();
    await demoContract.waitForDeployment(); // Wait for the contract to be deployed New in Hardhat 2.10.0
    //Який контракт ми розгорнули? Яка у нього адреса?
    console.log("Demo contract deployed to:", await demoContract.getAddress());
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });