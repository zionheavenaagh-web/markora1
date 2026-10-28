const form = document.getElementById("studentForm");
const recordsBody = document.getElementById("recordsBody");
const message = document.getElementById("message");

const students = [];

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("studentName").value.trim();
    const roll = document.getElementById("rollNumber").value.trim();

    const marks = [];

    for (let i = 1; i <= 5; i++) {
        const input = document.getElementById("mark" + i);

        if (input.value === "" ||
            Number(input.value) < 0 ||
            Number(input.value) > 100) {
            message.textContent = "Enter marks between 0 and 100.";
            return;
        }

        marks.push(Number(input.value));
    }

    if (students.some(s => s.roll.toLowerCase() === roll.toLowerCase())) {
        message.textContent = "This roll number already exists.";
        return;
    }

    const total = marks.reduce((sum, mark) => sum + mark, 0);
    const percentage = total / 5;

    // Example rule: at least 35 marks in every subject.
    const result = marks.every(mark => mark >= 35) ? "PASS" : "FAIL";

    students.push({ name, roll, total, percentage, result });

    recordsBody.innerHTML = "";

    students.forEach(function (student) {
        const row = document.createElement("tr");

        [
            student.roll,
            student.name,
            student.total,
            student.percentage.toFixed(2) + "%",
            student.result
        ].forEach(function (value) {
            const cell = document.createElement("td");
            cell.textContent = value;
            row.appendChild(cell);
        });

        recordsBody.appendChild(row);
    });

    message.textContent = "Student record added successfully!";
    form.reset();
});

form.addEventListener("reset", function () {
    message.textContent = "";
});