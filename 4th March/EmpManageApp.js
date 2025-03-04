async function addEmployeeFromHTML() {
    event.preventDefault();
    const empName = document.getElementById('empName').value.trim();
    const empAge = document.getElementById('empAge').value.trim();
    const empDesignation = document.getElementById('empDesignation').value;

    const parsedAge = parseInt(empAge);

    const addResult = document.getElementById('addResult');
    addResult.innerHTML = '';

    if (!empName || !empAge || isNaN(parsedAge) || parsedAge <= 0) {
        const p = document.createElement('p');
        p.textContent = "Please fill in all fields with valid data.";
        addResult.appendChild(p);
        return;
    }

    const employeeData = { 
        name: empName,
        age: parsedAge,
        designation: empDesignation
    };

    try {
        const response = await fetch('http://localhost:8080/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employeeData)
        });

        if (response.ok) {
            const resultText = await response.text();
            const p = document.createElement('p');
            p.textContent = resultText;
            addResult.appendChild(p);
            document.getElementById('addEmployeeForm').reset();
        } else {
            let errorMessage = `Error adding employee. Status: ${response.status} ${response.statusText}`;
            const p = document.createElement('p');
            p.textContent = errorMessage;
            p.style.color = 'red';
            addResult.appendChild(p);
            console.error('Error response:', response);
        }

    } catch (error) {
        console.error('Fetch error:', error);
    }
}

async function searchEmployeeFromHTML() {
    event.preventDefault();
    const empId = document.getElementById('searchEmpId').value.trim();

    const searchResult = document.getElementById('searchResult');
    searchResult.innerHTML = '';

    if (!empId || isNaN(parseInt(empId))) {
        const p = document.createElement('p');
        p.textContent = "Please enter a valid employee ID.";
        searchResult.appendChild(p);
        return;
    }

    const searchUrl = `http://localhost:8080/get/${empId}`;
    const response = await fetch(searchUrl);
    if(response.ok) {
        const employees = await response.json();
        if (employees && employees.length > 0) { 
            const employee = employees[0];
            const p = document.createElement('p');
            p.textContent = `Employee found: ID: ${employee.id}, Name: ${employee.name}, Age: ${employee.age}, Designation: ${employee.designation}, Salary: ${employee.salary}`;
            searchResult.appendChild(p);
        } else {
            const p = document.createElement('p');
            p.textContent = `No employee found with ID: ${empId}`;
            searchResult.appendChild(p);
        }
    } else {
        let errorMessage = `Error fetching employee. Status: ${response.status} ${response.statusText}`;
        const p = document.createElement('p');
        p.textContent = errorMessage;
        p.style.color ='red';
        searchResult.appendChild(p);
        console.error('Error response:', response);
    }
}

async function displayEmployeesFromHTML() {
    event.preventDefault();

    const employeeList = document.getElementById('employeeList');
    employeeList.innerHTML = '';

    const searchUrl = `http://localhost:8080/getall`;
    const response = await fetch(searchUrl);
    if(response.ok) {
        const employees = await response.json();
        if (employees && employees.length > 0) { 
            employees.forEach(employee => {
                const li = document.createElement('li');
                li.textContent = `ID: ${employee.id}, Name: ${employee.name}, Age: ${employee.age}, Designation: ${employee.designation}, Salary: ${employee.salary}`;
                employeeList.appendChild(li);
            });
        } else {
            const p = document.createElement('p');
            p.textContent = `No employee found with ID: ${empId}`;
            searchResult.appendChild(p);
        }
    } else {
        let errorMessage = `Error fetching employee. Status: ${response.status} ${response.statusText}`;
        const p = document.createElement('p');
        p.textContent = errorMessage;
        p.style.color ='red';
        searchResult.appendChild(p);
        console.error('Error response:', response);
    }
}

async function removeEmployeeFromHTML() {
    event.preventDefault();
    const removeempId = document.getElementById('removeEmpId').value.trim();

    const removeResult = document.getElementById('removeResult');
    removeResult.innerHTML = '';

    if (!removeempId ) {
        const p = document.createElement('p');
        p.textContent = "Please enter a valid employee ID";
        removeResult.appendChild(p);
        return;
    }

    const removeUrl = `http://localhost:8080/delete/${removeempId}`;
    const response = await fetch(removeUrl, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    });
    const responseText = await response.text();
    if (response.ok) {
        const p = document.createElement('p');
        p.textContent = responseText;
        removeResult.appendChild(p);
        document.getElementById('removeEmpId').value = '';
        document.getElementById('addEmployeeForm').reset();
    } else {
        let errorMessage = `Error removing employee. Status: ${response.status} ${response.statusText}`;
        const p = document.createElement('p');
        p.textContent = errorMessage;
        p.style.color = 'red';
        removeResult.appendChild(p);
        console.error('Error response:', response);
    }
}