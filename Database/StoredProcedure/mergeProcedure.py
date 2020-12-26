import os 
import mysql.connector 
from mysql.connector import Error

def execute_query(connection, query_text):
    connection.execute(query_text, multi=True)



def compile_sp(connection):
    executed = 0
    dir_entries = os.listdir(".")
    for entry in dir_entries:            
        extensions = entry.split(".")
        if extensions[len(extensions) - 1] == 'sql': 
            print("opening {}".format(entry))
            with open(entry) as stored_proc_script:
                queryText = stored_proc_script.read() 
                execute_query(connection, queryText)
                executed += 1
    print("executed {} sp".format(executed))

if __name__ == "__main__":
    db = mysql.connector.connect(
        host="localhost",
        user="root",
        password="root",
        database="asktoreply"
        )
    connection = db.cursor()
    compile_sp(connection)