-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 05, 2017 at 06:04 PM
-- Server version: 5.7.15-log
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `CarParkSystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `car_Reg` varchar(45) NOT NULL,
  `account_ID` int NOT NULL,
  `make` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `client_ID` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `parking_spot`
--

CREATE TABLE `parking_spot` (
  `spot_ID` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_ID` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `account_ID` int NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(45) NOT NULL,
  `second_Name` varchar(45) NOT NULL,
  `address_line1` varchar(45) NOT NULL,
  `address_line2` varchar(45) NOT NULL,
  `county` varchar(45) NOT NULL,
  `phone` int(10) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `staff_ID` varchar(45) DEFAULT NULL,
  `client_ID` varchar(45) DEFAULT NULL,
  `spot_ID` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_Reg`,`account_ID`),
  ADD KEY `account_ID_FK` (`account_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`client_ID`);

--
-- Indexes for table `parking_spot`
--
ALTER TABLE `parking_spot`
  ADD PRIMARY KEY (`spot_ID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`account_ID`),
  ADD KEY `staff_ID_FK` (`staff_ID`),
  ADD KEY `client_ID_FK` (`client_ID`),
  ADD KEY `spot_ID` (`spot_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `account_ID_FK` FOREIGN KEY (`account_ID`) REFERENCES `users` (`account_ID`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `client_ID_FK` FOREIGN KEY (`client_ID`) REFERENCES `customer` (`client_ID`),
  ADD CONSTRAINT `spot_ID` FOREIGN KEY (`spot_ID`) REFERENCES `parking_spot` (`spot_ID`),
  ADD CONSTRAINT `staff_ID_FK` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`staff_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
