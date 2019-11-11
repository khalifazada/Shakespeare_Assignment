import csv
import string

txt_file_name = 'shakespeare.txt'
csv_file_name = 'word_count.csv'

# read and split by whitespace
with open(txt_file_name, 'r') as raw:
	text = raw.read().split()

# remove punctuation
words = [element.lower().translate(str.maketrans('','', string.punctuation)) for element in text]

word_dict = dict()
for word in words:
	if word in word_dict:
		word_dict[word] += 1
	else:
		word_dict[word] = 1

with open(csv_file_name, 'w') as csv_file:
	csv_writer = csv.writer(csv_file, delimiter=',')
	for key, value in word_dict.items():
		csv_writer.writerow([key, value])
