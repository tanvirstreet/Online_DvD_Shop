-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2016 at 10:02 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dvd_shop`
--
CREATE DATABASE IF NOT EXISTS `dvd_shop` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dvd_shop`;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
`ctg_id` int(11) NOT NULL,
  `ctg_name` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ctg_id`, `ctg_name`) VALUES
(1, 'Action'),
(2, 'Horror');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`com_id` int(11) NOT NULL,
  `dvd_id` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `comment` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`com_id`, `dvd_id`, `cus_id`, `comment`, `date`) VALUES
(1, 1, 1, 'Not Good enough', '2016-07-05');

-- --------------------------------------------------------

--
-- Table structure for table `customer_info`
--

CREATE TABLE IF NOT EXISTS `customer_info` (
`cus_id` int(11) NOT NULL,
  `usr_id` int(11) NOT NULL,
  `cus_name` varchar(100) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `dob` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_info`
--

INSERT INTO `customer_info` (`cus_id`, `usr_id`, `cus_name`, `gender`, `dob`) VALUES
(1, 2, 'Tanvir Hossain', 'male', '2016-05-16'),
(2, 3, 'Mamun', 'male', '2016-07-06');

-- --------------------------------------------------------

--
-- Table structure for table `dvd_info`
--

CREATE TABLE IF NOT EXISTS `dvd_info` (
`dvd_id` int(11) NOT NULL,
  `ctg_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `actor` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dvd_info`
--

INSERT INTO `dvd_info` (`dvd_id`, `ctg_id`, `title`, `actor`) VALUES
(1, 1, 'Mission Imposible', 'Tom Cruse'),
(2, 1, 'Big Herro', 'Jack'),
(3, 2, 'Hkasd', 'asf'),
(4, 1, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
`req_id` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `dvd_name` varchar(100) NOT NULL,
  `comment` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`req_id`, `cus_id`, `dvd_name`, `comment`) VALUES
(1, 2, 'Last Watch', 'asd hha asd'),
(2, 1, 'Zoodopia', 'popeya'),
(3, 2, 'The A Team', 'Hosin');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`user_id` int(11) NOT NULL,
  `user_name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` int(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `password`, `role`) VALUES
(1, 'admin', '000', 1),
(2, 'tanvir', '123', 2),
(3, 'mamun', '123', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`ctg_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`com_id`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
 ADD PRIMARY KEY (`cus_id`);

--
-- Indexes for table `dvd_info`
--
ALTER TABLE `dvd_info`
 ADD PRIMARY KEY (`dvd_id`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
 ADD PRIMARY KEY (`req_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
MODIFY `ctg_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `com_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `customer_info`
--
ALTER TABLE `customer_info`
MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `dvd_info`
--
ALTER TABLE `dvd_info`
MODIFY `dvd_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
MODIFY `req_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
