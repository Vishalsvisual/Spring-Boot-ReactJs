import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employeesList";
//const EMPLOYEE_API_ADD = "http://localhost:8080/api/v1/employeesList"

class EmployeeService {
    getEmployees() {
       return axios.get(`${EMPLOYEE_API_BASE_URL}?skip=0&limit=100`);
    }

    createEmployee(empObj){
        console.log(`Employee : ${JSON.stringify(empObj)}`);
        return axios.post(EMPLOYEE_API_BASE_URL, empObj);
    }

    getEmployeeById(empId){
        return axios.get(`${EMPLOYEE_API_BASE_URL}/${empId}`);
    }

    updateEmployee(empObj, empId){
        console.log(`Update Employee :: ${empObj}`);
        return axios.put(`${EMPLOYEE_API_BASE_URL}/${empId}`, empObj);
    }

    deleteEmployee(id) {
        return axios.delete(`${EMPLOYEE_API_BASE_URL}/${id}`);
    }
}

export default new EmployeeService();