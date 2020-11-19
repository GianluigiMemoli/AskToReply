import os 

def main():
    dir_entries = os.listdir(".")
    mergedText = ""    
    for entry in dir_entries:            
        extensions = entry.split(".")
        if extensions[len(extensions) - 1] == 'sql': 
            print("opening {}".format(entry))
            with open(entry) as stored_proc_script:
                mergedText += stored_proc_script.read() + "\n"
    
    
    
    with open("merged.sql", "w") as merged: 
        merged.write(mergedText)

if __name__ == "__main__":
    main()