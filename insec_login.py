# insecure_login.py
import sqlite3
import sys

DB_PATH = "users.db"  # hardcoded DB path

def init_db():
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)")
    cur.execute("DELETE FROM users")
    cur.execute("INSERT INTO users (username, password) VALUES ('alice', 'password123')")
    cur.execute("INSERT INTO users (username, password) VALUES ('bob', 'hunter2')")
    conn.commit()
    conn.close()

def insecure_login(username: str, password: str) -> bool:
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()

    # VULNERABLE: SQL injection via string formatting
    query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
    print("[DEBUG] Executing:", query)

    cur.execute(query)  # untrusted input goes straight into the query
    row = cur.fetchone()
    conn.close()
    return row is not None

def main():
    if len(sys.argv) != 3:
        print("Usage: python insecure_login.py <username> <password>")
        sys.exit(1)

    username = sys.argv[1]
    password = sys.argv[2]

    if insecure_login(username, password):
        print("Login successful")
    else:
        print("Login failed")

if __name__ == "__main__":
    init_db()
    main()
