import tkinter as tk

def calculate_bmi():
    height = float(height_entry.get())
    weight = float(weight_entry.get())
    bmi = weight / (height ** 2)
    result_label.configure(text=f"BMI: {bmi:.2f}")

root = tk.Tk()
root.title("BMI Calculator")

# Create labels
height_label = tk.Label(root, text="Height (m):")
height_label.pack()

weight_label = tk.Label(root, text="Weight (kg):")
weight_label.pack()

result_label = tk.Label(root, text="BMI:")
result_label.pack()

# Create entry fields
height_entry = tk.Entry(root)
height_entry.pack()

weight_entry = tk.Entry(root)
weight_entry.pack()

# Create calculate button
calculate_button = tk.Button(root, text="Calculate", command=calculate_bmi)
calculate_button.pack()

root.mainloop()
