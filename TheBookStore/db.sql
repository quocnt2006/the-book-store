USE [master]
GO
/****** Object:  Database [TheStoreBook]    Script Date: 26/05/2021 2:56:15 pm ******/
CREATE DATABASE [TheStoreBook]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheStoreBook', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheStoreBook.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TheStoreBook_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheStoreBook_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TheStoreBook] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheStoreBook].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheStoreBook] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheStoreBook] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheStoreBook] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheStoreBook] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheStoreBook] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheStoreBook] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [TheStoreBook] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheStoreBook] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheStoreBook] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheStoreBook] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheStoreBook] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheStoreBook] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheStoreBook] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheStoreBook] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheStoreBook] SET  ENABLE_BROKER 
GO
ALTER DATABASE [TheStoreBook] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheStoreBook] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheStoreBook] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheStoreBook] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheStoreBook] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheStoreBook] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheStoreBook] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheStoreBook] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TheStoreBook] SET  MULTI_USER 
GO
ALTER DATABASE [TheStoreBook] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheStoreBook] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheStoreBook] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheStoreBook] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TheStoreBook] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TheStoreBook]
GO
/****** Object:  Table [dbo].[Tbl_Book]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_Book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NULL,
	[price] [decimal](18, 10) NULL,
	[image] [nvarchar](max) NULL,
	[author] [nvarchar](100) NULL,
	[description] [nvarchar](100) NULL,
	[quantity] [int] NULL,
	[importDate] [date] NULL,
	[status] [varchar](50) NOT NULL,
	[categoryId] [int] NULL,
 CONSTRAINT [PK_Tbl_Book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tbl_Category]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](100) NULL,
 CONSTRAINT [PK_Tbl_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tbl_Discount]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_Discount](
	[code] [int] NOT NULL,
	[cusId] [nvarchar](50) NULL,
	[isUsed] [bit] NULL,
	[discount] [decimal](18, 0) NULL,
 CONSTRAINT [PK_Tbl_DIscount] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tbl_Order]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_Order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderDate] [datetime] NULL,
	[cusId] [nvarchar](50) NULL,
	[total] [float] NULL,
	[orderDay] [date] NULL,
 CONSTRAINT [PK_Tbl_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tbl_OrderDetail]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_OrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bookId] [int] NULL,
	[quantity] [int] NULL,
	[unitPrice] [decimal](18, 10) NULL,
	[total] [decimal](18, 10) NOT NULL,
	[orderId] [int] NULL,
	[title] [nvarchar](100) NULL,
 CONSTRAINT [PK_Tbl_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tbl_User]    Script Date: 26/05/2021 2:56:15 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tbl_User](
	[id] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[userName] [nvarchar](100) NULL,
	[isAdmin] [bit] NULL,
	[status] [nvarchar](50) NULL,
 CONSTRAINT [PK_Tbl_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Tbl_Book] ON 

INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (2, N'Java EE', CAST(5.0000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Microsoft', N'Ver 1.5', 83, CAST(N'2019-11-14' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (3, N'Jack Ma', CAST(4.0000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Jack Ma', N'Ver 1.0', 85, CAST(N'2019-11-25' AS Date), N'Inactive', 2)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (4, N'Photoshop', CAST(3.9000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Davinci', N'Ver 5.0', 90, CAST(N'2019-11-26' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (5, N'BlockChain', CAST(4.5000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Salah', N'Ver 0.8', 99, CAST(N'2019-11-26' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (6, N'A', CAST(4.3100000000 AS Decimal(18, 10)), N'D:\Download\New folder\489396.jpg', N'Obama1', N'Var 11', 77, CAST(N'2021-05-16' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (9, N'Baby', CAST(10.0000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Bayby Shark', N'For Fun', 98, CAST(N'2019-11-27' AS Date), N'Inactive', 2)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (10, N'Mario', CAST(4.5000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'Game', N'Funny', 95, CAST(N'2019-11-27' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (11, N'Java Desk', CAST(3.8000000000 AS Decimal(18, 10)), N'E:\Lab231_FPT\TheBookStore\web\IMG\b.jpg', N'VanTTN', N'Ver 5', 99, CAST(N'2019-11-28' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (12, N'Java for Absolute Beginners', CAST(26.9900000000 AS Decimal(18, 10)), N'.\IMG\9781484237779.jpg', N'Cosmina, Iuliana', N'Learn to Program the Fundamentals the Java 9+ Way', 287, CAST(N'2021-05-16' AS Date), N'Active', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (13, N'1', CAST(1.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\Big-Truck-Background-HD.jpg', N'12', N'12', 12, CAST(N'2021-05-17' AS Date), N'Inactive', 2)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (14, N'111', CAST(1111.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\chinese-house.jpg', N'11', N'11', 111, CAST(N'2021-05-17' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (15, N'11', CAST(111.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\good-looking-truck-august-2017-1.jpg', N'1111', N'111', 11, CAST(N'2021-05-17' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (16, N'111', CAST(111.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\hNBlSH.jpg', N'1111', N'111', 111, CAST(N'2021-05-17' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (17, N'111', CAST(1111.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\hNBlSH.jpg', N'111', N'11', 111, CAST(N'2021-05-17' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (18, N'1111', CAST(111.0000000000 AS Decimal(18, 10)), N'D:\Download\New folder\Ruins-Wallpaper-09687.jpg', N'111', N'111', 111, CAST(N'2021-05-17' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (19, N'The Clean Coder', CAST(33.4300000000 AS Decimal(18, 10)), N'.\IMG\the-clean-coder-pdf.jpg', N'Robert C.Martin', N'A Code of Conduct for Professional Programmers', 38, CAST(N'2021-05-21' AS Date), N'Active', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (20, N'The pragmatic Programmer', CAST(10.6800000000 AS Decimal(18, 10)), N'.\IMG\tpp20.jpg', N'Andrew Hunt, David Thomas', N'From journeyman to master', 42, CAST(N'2021-05-21' AS Date), N'Active', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (21, N'Business Adventures', CAST(7.9500000000 AS Decimal(18, 10)), N'.\IMG\5df14aacfd9db226e74356c2.jpg', N'John Brooks', N'Twelve Classic Tales from the World of Wall', 45, CAST(N'2021-05-21' AS Date), N'Active', 2)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (22, N'The Intelligent Investor', CAST(14.2900000000 AS Decimal(18, 10)), N'.\IMG\5df14adffd9db2138f2ec575.jpg', N'Benjamin Graham', N'The Definitive Book on Value Investing. A Book of Practical Counsel (Revised Edition)', 40, CAST(N'2021-05-21' AS Date), N'Active', 2)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (23, N'Designing Brand Identity', CAST(33.8200000000 AS Decimal(18, 10)), N'.\IMG\51rxRlDs7JL._SX367_BO1,204,203,200_.jpg', N'Alina Wheeler', N'An Essential Guide for the Whole Branding Team 5th Edition', 31, CAST(N'2021-05-21' AS Date), N'Active', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (24, N'The Elements of Typographic Style', CAST(18.3600000000 AS Decimal(18, 10)), N'C:\Users\ASUS\Desktop\img_lab\41aX0fEkNwL._SX291_BO1,204,203,200_.jpg', N'Robert Bringhurst', N'Version 4.0: 20th Anniversary Edition', 80, CAST(N'2021-05-22' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (25, N'adsads', CAST(123.0000000000 AS Decimal(18, 10)), N'C:\Users\ASUS\Desktop\img_lab\the-clean-coder-pdf.jpg', N'asdasdasda', N'sdadas', 121, CAST(N'2021-05-22' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (29, N'asd', CAST(1212.0000000000 AS Decimal(18, 10)), N'C:\Users\ASUS\Desktop\img_lab\41aX0fEkNwL._SX291_BO1,204,203,200_.jpg', N'11212', N'asd', 12, CAST(N'2021-05-25' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (30, N'asd', CAST(1212.0000000000 AS Decimal(18, 10)), N'', N'sadasasd', N'asd', 12, CAST(N'2021-05-25' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (31, N'asda', CAST(123.0000000000 AS Decimal(18, 10)), N'C:\Users\ASUS\Desktop\img_lab\tpp20.jpg', N'12312312', N'sdasda', 13213, CAST(N'2021-05-25' AS Date), N'Inactive', 3)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (32, N'asdasdasd', CAST(123.0000000000 AS Decimal(18, 10)), N'.\IMG\47227882_2186245264931803_392485463107043328_n.jpg', N'asd', N'asdas', 12, CAST(N'2021-05-26' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (33, N'asdasd', CAST(1221.0000000000 AS Decimal(18, 10)), N'.\IMG\1123.jpg', N'asdasddas', N'dasasddas', 1212, CAST(N'2021-05-26' AS Date), N'Inactive', 1)
INSERT [dbo].[Tbl_Book] ([id], [title], [price], [image], [author], [description], [quantity], [importDate], [status], [categoryId]) VALUES (34, N'Ã¡dasd', CAST(12321.0000000000 AS Decimal(18, 10)), N'.\IMG\1123.jpg', N'Ã¢sdasdads', N'Ã¡dasdasd', 123123132, CAST(N'2021-05-26' AS Date), N'Inactive', 1)
SET IDENTITY_INSERT [dbo].[Tbl_Book] OFF
GO
SET IDENTITY_INSERT [dbo].[Tbl_Category] ON 

INSERT [dbo].[Tbl_Category] ([id], [categoryName]) VALUES (1, N'IT')
INSERT [dbo].[Tbl_Category] ([id], [categoryName]) VALUES (2, N'Bussiness')
INSERT [dbo].[Tbl_Category] ([id], [categoryName]) VALUES (3, N'Graphic')
SET IDENTITY_INSERT [dbo].[Tbl_Category] OFF
GO
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (10129032, N'quocnt1', 1, CAST(20 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (14637940, NULL, 0, CAST(50 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (15571344, NULL, 0, CAST(10 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (31266827, NULL, 0, CAST(5 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (31721728, NULL, 0, CAST(5 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (33247624, NULL, 0, CAST(30 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (33376548, N'quocnt1', 1, CAST(10 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (33771325, NULL, 0, CAST(10 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (35261178, NULL, 0, CAST(2 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (43862178, NULL, 0, CAST(23 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (44631106, NULL, 0, CAST(10 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (61815389, NULL, 0, CAST(20 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (66752999, NULL, 0, CAST(12 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (72719717, NULL, 0, CAST(10 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (76514813, N'quocnt', 1, CAST(20 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (76789330, N'quocnt', 1, CAST(30 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (79525452, NULL, 0, CAST(20 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (90590996, NULL, 0, CAST(5 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (92770602, NULL, 0, CAST(5 AS Decimal(18, 0)))
INSERT [dbo].[Tbl_Discount] ([code], [cusId], [isUsed], [discount]) VALUES (95952416, NULL, 0, CAST(2 AS Decimal(18, 0)))
GO
SET IDENTITY_INSERT [dbo].[Tbl_Order] ON 

INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (8, CAST(N'2021-05-20T20:06:22.170' AS DateTime), N'quocnt', 8.81, CAST(N'2021-05-20' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (11, CAST(N'2021-05-20T20:19:52.003' AS DateTime), N'quocnt', 17.62, CAST(N'2021-05-20' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (12, CAST(N'2021-05-21T15:45:23.983' AS DateTime), N'quocnt', 1822.04, CAST(N'2021-05-21' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (13, CAST(N'2021-05-22T11:57:48.117' AS DateTime), N'quocnt', 130.41, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (14, CAST(N'2021-05-22T12:03:21.027' AS DateTime), N'quocnt', 18.893, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (15, CAST(N'2021-05-22T12:03:50.580' AS DateTime), N'quocnt', 42.294, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (16, CAST(N'2021-05-22T12:09:02.627' AS DateTime), N'quocnt', 18.893, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (17, CAST(N'2021-05-22T12:09:56.623' AS DateTime), N'quocnt', 26.99, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (18, CAST(N'2021-05-22T12:13:07.940' AS DateTime), N'quocnt', 23.674, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (19, CAST(N'2021-05-22T12:13:50.817' AS DateTime), N'quocnt', 683.7, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (20, CAST(N'2021-05-22T12:14:29.637' AS DateTime), N'quocnt', 600.8, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (21, CAST(N'2021-05-22T15:14:17.017' AS DateTime), N'quocnt', 26.99, CAST(N'2021-05-22' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (22, CAST(N'2021-05-24T13:26:59.060' AS DateTime), N'quocnt', 60.81, CAST(N'2021-05-24' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (23, CAST(N'2021-05-24T13:27:17.880' AS DateTime), N'quocnt1', 26.99, CAST(N'2021-05-24' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (24, CAST(N'2021-05-24T13:51:18.757' AS DateTime), N'quocnt1', 586.93, CAST(N'2021-05-24' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (25, CAST(N'2021-05-24T13:53:39.427' AS DateTime), N'quocnt1', 21.592, CAST(N'2021-05-24' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (26, CAST(N'2021-05-25T20:48:29.730' AS DateTime), N'quocnt1', 595.872, CAST(N'2021-05-25' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (27, CAST(N'2021-05-25T20:48:57.847' AS DateTime), N'quocnt1', 161.59, CAST(N'2021-05-25' AS Date))
INSERT [dbo].[Tbl_Order] ([id], [orderDate], [cusId], [total], [orderDay]) VALUES (28, CAST(N'2021-05-26T14:55:01.247' AS DateTime), N'quocnt', 60.42, CAST(N'2021-05-26' AS Date))
SET IDENTITY_INSERT [dbo].[Tbl_Order] OFF
GO
SET IDENTITY_INSERT [dbo].[Tbl_OrderDetail] ON 

INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (13, 6, 1, CAST(4.3100000000 AS Decimal(18, 10)), CAST(4.3100000000 AS Decimal(18, 10)), 8, N'A')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (14, 10, 1, CAST(4.5000000000 AS Decimal(18, 10)), CAST(4.5000000000 AS Decimal(18, 10)), 8, N'Mario')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (15, 6, 2, CAST(4.3100000000 AS Decimal(18, 10)), CAST(8.6200000000 AS Decimal(18, 10)), 11, N'A')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (16, 10, 2, CAST(4.5000000000 AS Decimal(18, 10)), CAST(9.0000000000 AS Decimal(18, 10)), 11, N'Mario')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (17, 19, 17, CAST(33.4300000000 AS Decimal(18, 10)), CAST(568.3100000000 AS Decimal(18, 10)), 12, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (18, 20, 16, CAST(10.6800000000 AS Decimal(18, 10)), CAST(170.8800000000 AS Decimal(18, 10)), 12, N'The pragmatic Programmer')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (19, 21, 15, CAST(7.9500000000 AS Decimal(18, 10)), CAST(119.2500000000 AS Decimal(18, 10)), 12, N'Business Adventures')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (20, 22, 14, CAST(14.2900000000 AS Decimal(18, 10)), CAST(200.0600000000 AS Decimal(18, 10)), 12, N'The Intelligent Investor')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (21, 23, 13, CAST(33.8200000000 AS Decimal(18, 10)), CAST(439.6600000000 AS Decimal(18, 10)), 12, N'Designing Brand Identity')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (22, 12, 12, CAST(26.9900000000 AS Decimal(18, 10)), CAST(323.8800000000 AS Decimal(18, 10)), 12, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (23, 20, 10, CAST(10.6800000000 AS Decimal(18, 10)), CAST(106.8000000000 AS Decimal(18, 10)), 13, N'The pragmatic Programmer')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (24, 21, 10, CAST(7.9500000000 AS Decimal(18, 10)), CAST(79.5000000000 AS Decimal(18, 10)), 13, N'Business Adventures')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (25, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 14, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (26, 19, 1, CAST(33.4300000000 AS Decimal(18, 10)), CAST(33.4300000000 AS Decimal(18, 10)), 15, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (27, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 15, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (28, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 16, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (29, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 17, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (30, 23, 1, CAST(33.8200000000 AS Decimal(18, 10)), CAST(33.8200000000 AS Decimal(18, 10)), 18, N'Designing Brand Identity')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (31, 19, 10, CAST(33.4300000000 AS Decimal(18, 10)), CAST(334.3000000000 AS Decimal(18, 10)), 19, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (32, 21, 10, CAST(7.9500000000 AS Decimal(18, 10)), CAST(79.5000000000 AS Decimal(18, 10)), 19, N'Business Adventures')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (33, 12, 10, CAST(26.9900000000 AS Decimal(18, 10)), CAST(269.9000000000 AS Decimal(18, 10)), 19, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (34, 22, 10, CAST(14.2900000000 AS Decimal(18, 10)), CAST(142.9000000000 AS Decimal(18, 10)), 20, N'The Intelligent Investor')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (35, 23, 10, CAST(33.8200000000 AS Decimal(18, 10)), CAST(338.2000000000 AS Decimal(18, 10)), 20, N'Designing Brand Identity')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (36, 12, 10, CAST(26.9900000000 AS Decimal(18, 10)), CAST(269.9000000000 AS Decimal(18, 10)), 20, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (37, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 21, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (38, 23, 1, CAST(33.8200000000 AS Decimal(18, 10)), CAST(33.8200000000 AS Decimal(18, 10)), 22, N'Designing Brand Identity')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (39, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 22, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (40, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 23, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (41, 19, 12, CAST(33.4300000000 AS Decimal(18, 10)), CAST(401.1600000000 AS Decimal(18, 10)), 24, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (42, 22, 13, CAST(14.2900000000 AS Decimal(18, 10)), CAST(185.7700000000 AS Decimal(18, 10)), 24, N'The Intelligent Investor')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (43, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 25, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (44, 23, 10, CAST(33.8200000000 AS Decimal(18, 10)), CAST(338.2000000000 AS Decimal(18, 10)), 26, N'Designing Brand Identity')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (45, 12, 12, CAST(26.9900000000 AS Decimal(18, 10)), CAST(323.8800000000 AS Decimal(18, 10)), 26, N'Java for Absolute Beginners')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (46, 19, 1, CAST(33.4300000000 AS Decimal(18, 10)), CAST(33.4300000000 AS Decimal(18, 10)), 27, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (47, 20, 12, CAST(10.6800000000 AS Decimal(18, 10)), CAST(128.1600000000 AS Decimal(18, 10)), 27, N'The pragmatic Programmer')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (48, 19, 1, CAST(33.4300000000 AS Decimal(18, 10)), CAST(33.4300000000 AS Decimal(18, 10)), 28, N'The Clean Coder')
INSERT [dbo].[Tbl_OrderDetail] ([id], [bookId], [quantity], [unitPrice], [total], [orderId], [title]) VALUES (49, 12, 1, CAST(26.9900000000 AS Decimal(18, 10)), CAST(26.9900000000 AS Decimal(18, 10)), 28, N'Java for Absolute Beginners')
SET IDENTITY_INSERT [dbo].[Tbl_OrderDetail] OFF
GO
INSERT [dbo].[Tbl_User] ([id], [password], [userName], [isAdmin], [status]) VALUES (N'quocnt', N'123456', N'Quoc Nguyen Tien', 0, N'Active')
INSERT [dbo].[Tbl_User] ([id], [password], [userName], [isAdmin], [status]) VALUES (N'quocnt1', N'123456', N'Quoc 2', 0, N'Active')
INSERT [dbo].[Tbl_User] ([id], [password], [userName], [isAdmin], [status]) VALUES (N'quocntadmin', N'123456', N'Admin 1', 1, N'Active')
GO
ALTER TABLE [dbo].[Tbl_Book] ADD  CONSTRAINT [DF_Tbl_Book_importDate]  DEFAULT (getdate()) FOR [importDate]
GO
ALTER TABLE [dbo].[Tbl_Order] ADD  CONSTRAINT [DF_Tbl_Order_orderDate]  DEFAULT (getdate()) FOR [orderDate]
GO
ALTER TABLE [dbo].[Tbl_Order] ADD  CONSTRAINT [DF_Tbl_Order_orderDay]  DEFAULT (getdate()) FOR [orderDay]
GO
ALTER TABLE [dbo].[Tbl_Book]  WITH CHECK ADD  CONSTRAINT [FK_Tbl_Book_Tbl_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Tbl_Category] ([id])
GO
ALTER TABLE [dbo].[Tbl_Book] CHECK CONSTRAINT [FK_Tbl_Book_Tbl_Category]
GO
ALTER TABLE [dbo].[Tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_Tbl_Order_Tbl_User] FOREIGN KEY([cusId])
REFERENCES [dbo].[Tbl_User] ([id])
GO
ALTER TABLE [dbo].[Tbl_Order] CHECK CONSTRAINT [FK_Tbl_Order_Tbl_User]
GO
ALTER TABLE [dbo].[Tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Book] FOREIGN KEY([bookId])
REFERENCES [dbo].[Tbl_Book] ([id])
GO
ALTER TABLE [dbo].[Tbl_OrderDetail] CHECK CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Book]
GO
ALTER TABLE [dbo].[Tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Tbl_Order] ([id])
GO
ALTER TABLE [dbo].[Tbl_OrderDetail] CHECK CONSTRAINT [FK_Tbl_OrderDetail_Tbl_Order]
GO
USE [master]
GO
ALTER DATABASE [TheStoreBook] SET  READ_WRITE 
GO
