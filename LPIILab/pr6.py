# HOSPITAL EXPERT SYSTEM

print("========== HOSPITAL EXPERT SYSTEM ==========")

name = input("Enter Patient Name: ")

score = 0

print("\nAnswer all questions with yes/no\n")

# Questions
questions = [
    ("Do you have fever? ", 2),
    ("Do you have cough? ", 2),
    ("Do you have chest pain? ", 3),
    ("Do you feel tired frequently? ", 1),
    ("Do you have breathing difficulty? ", 3),
    ("Do you have headache? ", 1),
    ("Do you have vomiting? ", 2),
    ("Do you have body pain? ", 1),
    ("Do you have sore throat? ", 1),
    ("Do you have dizziness? ", 2),
    ("Do you have stomach pain? ", 2),
    ("Do you have high blood pressure? ", 3),
    ("Do you have diabetes? ", 3),
    ("Do you have loss of appetite? ", 1),
    ("Do you have skin allergy/rashes? ", 2)
]

# Asking questions
for q, marks in questions:
    ans = input(q)

    if ans.lower() == "yes":
        score += marks

# Maximum score
max_score = sum(marks for q, marks in questions)

# Percentage
percentage = (score / max_score) * 100

# Output
print("\n========== MEDICAL REPORT ==========")
print("Patient Name :", name)
print("Total Score  :", score, "/", max_score)
print("Percentage   : {:.2f}%".format(percentage))

# Expert Decision
if percentage >= 75:
    print("\nCondition : CRITICAL")
    print("Advice    : Immediate hospitalization required.")
    print("Department: Emergency / ICU")

elif percentage >= 50:
    print("\nCondition : SERIOUS")
    print("Advice    : Consult doctor immediately.")
    print("Department: General Medicine")

elif percentage >= 30:
    print("\nCondition : MODERATE")
    print("Advice    : Medical checkup recommended.")
    print("Department: OPD")

else:
    print("\nCondition : NORMAL")
    print("Advice    : Maintain healthy lifestyle and rest.")
