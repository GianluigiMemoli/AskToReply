import os 

def main():
    dir_entries = os.listdir(".")
    with open("merged.sql", "w") as merged_script:
        for entry in dir_entries:            
            extensions = entry.split(".")
            print("opening {}\next {}".format(entry, extensions))
            if extensions[len(extensions) - 1] == 'sql': 
                print("opening {}".format(entry))
                with open(entry) as stored_proc_script:
                    merged_script.write(stored_proc_script.read())
                    merged_script.write("\n")
    

if __name__ == "__main__":
    main()