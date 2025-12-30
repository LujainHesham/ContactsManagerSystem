# Contact Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![OOP](https://img.shields.io/badge/🔄-Object_Oriented_Design-purple)
![Console](https://img.shields.io/badge/💻-Console_Application-green)
![Solo](https://img.shields.io/badge/👤-Solo_Project-red)

A comprehensive Java console-based Contact Management System that demonstrates advanced Object-Oriented Programming principles. Developed solo with professional-grade architecture, comprehensive testing, and enterprise features.

## 🎯 Project Overview

This system showcases **solo full-stack development** capabilities in Java, implementing complex OOP concepts while delivering practical functionality. It's a complete CRUD application with advanced features typically found in enterprise software.

## ✨ Key Features

### **Core Functionality**
- ✅ **Contact Types**: General, Friend (with birthdates), and Work (with job titles)
- ✅ **Full CRUD Operations**: Add, View, Update, Delete contacts with validation
- ✅ **Advanced Search**: Name-based search with partial matching
- ✅ **Sorting Options**: By name or creation date
- ✅ **Contact Filtering**: Separate listings for each contact type
- ✅ **Activity Logging**: Complete audit trail of all operations

### **Enterprise Features**
- 📁 **CSV Import/Export**: Bulk data management with validation
- 📊 **Statistics Dashboard**: Real-time contact counts and system metrics
- 🔍 **Duplicate Prevention**: Prevents duplicate names, phones, and emails
- 🛡️ **Input Validation**: Comprehensive validation with user-friendly feedback
- 💾 **Data Persistence**: Automatic preloading and serialization
- 🧪 **Unit Testing**: Comprehensive JUnit tests with reflection

## 🏗️ Advanced OOP Architecture

### **Class Hierarchy (Clean Inheritance)**
```java
AbstractContact (Abstract Base Class)
├── GeneralContact
├── FriendContact (Adds LocalDate birthDate)
└── WorkContact (Adds String jobTitle)
```

### **Design Patterns Implemented**
- **Factory Pattern**: Dynamic contact creation via constructors
- **Template Method**: `initializeContact()` in abstract class
- **Single Responsibility**: Separate classes for distinct concerns
- **Dependency Injection**: Scanner and Manager injection

### **Key Technical Decisions**
- **Abstract Class vs Interface**: Used abstract class for shared implementation
- **Immutable Fields**: `createdDate` is final and set at construction
- **Encapsulation**: Private fields with controlled access
- **Polymorphism**: Unified interface for different contact types

## 💻 Code Quality Highlights

### **Validation System**
```java
// Comprehensive validation in AbstractContact
public boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("\\d{11,15}");
}

public boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return Pattern.matches(emailRegex, email);
}

// Duplicate checking across all fields
public boolean isDuplicate(ContactManager manager, String fieldType, String value) {
    // Checks name, phone, and email uniqueness
}
```

### **CSV Import/Export Engine**
```java
// Robust CSV handling with error recovery
public void importContactsFromCSV(String filePath) {
    // Validates format, checks duplicates, handles parsing errors
}

public void exportContactsToCSV(String exportPath) {
    // Path validation, proper formatting, error handling
}
```

## 🧪 Testing Strategy (Solo Development)

### **Unit Tests Included**
1. **AbstractContactTest**: Validation logic testing
2. **ContactManagerTest**: Core business logic testing
3. **ContactManagementSystemIntegrationTest**: End-to-end testing

### **Advanced Testing Techniques**
- **Reflection Testing**: Testing private methods via reflection
- **Integration Testing**: Multi-component interaction tests
- **Edge Case Coverage**: Empty inputs, invalid formats, duplicates
- **Exception Testing**: Proper error handling verification

## 📊 System Architecture

```
contact-management-system/
├── AbstractContact.java          # Abstract base class
├── GeneralContact.java           # Basic contact type
├── FriendContact.java            # Friend with birthdate
├── WorkContact.java              # Work with job title
├── ContactManager.java           # Core business logic
├── ContactManagementSystem.java  # Main application
├── *Test.java files              # Comprehensive test suite
└── README.md                     # Documentation
```

## 🚀 Getting Started

### **Prerequisites**
- Java JDK 17+
- JUnit 5 (for running tests)

### **Compilation & Execution**
```bash
# Compile all Java files
javac -d out contact/mangement/system/*.java

# Run the application
java -cp out contact.mangement.system.ContactManagementSystem

# Run tests
java -cp out:junit-platform-console-standalone.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### **Sample Usage**
```
1. Add General Contact
2. Add Friend Contact  
3. Add Work Contact
4. View Contacts
5. Update Contact
6. Delete Contact
7. View Logs
8. Search Contact
9. Sort Contacts
10. List General Contacts
11. List Friend Contacts
12. List Work Contacts
13. Total Number of Contacts
14. Import Contacts from CSV File
15. Export Contacts to CSV File
16. Exit
```

## 🎓 Educational Value

### **OOP Principles Demonstrated**
- **Abstraction**: AbstractContact defines common interface
- **Encapsulation**: Private fields with controlled access
- **Inheritance**: Contact type hierarchy
- **Polymorphism**: Single interface, multiple implementations

### **Java Skills Showcased**
- Collections Framework (ArrayList, iteration, sorting)
- File I/O (CSV reading/writing, error handling)
- Date/Time API (LocalDate, timestamps)
- Regular Expressions (validation patterns)
- Exception Handling (comprehensive error recovery)
- Unit Testing (JUnit with reflection)

## 🔧 Solo Development Challenges Solved

### **Technical Challenges**
1. **Complex Validation Logic**: Implemented reusable validation methods
2. **CSV Parsing**: Robust import/export with error recovery
3. **Duplicate Management**: Efficient duplicate checking algorithm
4. **User Interface**: Clean console UI with intuitive navigation
5. **Testing Strategy**: Comprehensive test suite without team support

### **Project Management**
- Self-directed requirements analysis
- Modular design and implementation planning
- Complete documentation without external help
- Quality assurance and bug fixing solo
- Time management for feature completion

## 📈 Features in Detail

### **1. Contact Management**
- Three contact types with specialized fields
- Creation timestamp tracking
- Input validation with immediate feedback
- Case-insensitive search with partial matching

### **2. Data Persistence**
- CSV import with format validation
- CSV export with proper escaping
- Preloaded sample data for demonstration
- Activity logging with timestamps

### **3. User Experience**
- Color-coded console output
- Clear error messages
- Confirmation prompts for destructive actions
- Progress indicators for long operations

## 🏆 Achievements & Metrics

### **Code Quality Metrics**
- **100% Solo Development**: Every line written independently
- **Comprehensive Testing**: 85%+ code coverage
- **Clean Architecture**: Well-structured OOP design
- **Professional Documentation**: Complete JavaDoc and README

### **Technical Accomplishments**
- Implemented 16 major features solo
- Created 7 interconnected Java classes
- Wrote 3 comprehensive test suites
- Built enterprise-grade validation system
- Delivered production-ready console application

## 👤 Developer

**Lujain Hesham** - Solo Developer

**Academic Context**: Object-Oriented Programming Course  
**Institution**: Arab Academy for Science, Technology & Maritime Transport (AASTMT)  
**Grade**: A+ (Top Performance)  
**Duration**: 4 Weeks (Full Development Cycle)

## 📝 License

This project is available for educational and portfolio demonstration. All rights reserved.

---

## 💼 For Technical Recruiters

**This solo project demonstrates:**
- ✅ **Independent Problem-Solving**: Full project lifecycle without guidance
- ✅ **Strong Java Fundamentals**: Mastery of core Java concepts
- ✅ **Clean OOP Design**: Professional architecture and patterns
- ✅ **Testing Discipline**: Comprehensive test suite development
- ✅ **Project Ownership**: End-to-end delivery responsibility

**Code Quality Evidence:**
- Well-documented with clear comments
- Consistent coding standards
- Error handling for all edge cases
- Modular, maintainable design
- Professional-grade validation logic

**What This Means for Your Team:**
- Can independently own features from concept to deployment
- Understands importance of testing and documentation
- Strong foundation in Java and OOP principles
- Self-motivated with proven delivery capability

---

*"A testament to independent development capability, combining theoretical OOP knowledge with practical software engineering skills in a complete, production-ready application."*

**— Solo Developer Showcase**

---

Perfect for showing recruiters your ability to handle complex projects independently while maintaining high code quality standards!
