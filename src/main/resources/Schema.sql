CREATE TABLE users(
                      user_id SERIAL PRIMARY KEY,
                      first_name VARCHAR(50) NOT NULL,
                      last_name VARCHAR(50) NOT NULL,
                      gender VARCHAR(20) NOT NULL,
                      profile_url VARCHAR(255),
                      email VARCHAR(100) UNIQUE NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      is_enabled    bool         not null default true,
                      is_locked     bool         not null default false

);
CREATE TABLE workspaces (
                            workspace_id SERIAL PRIMARY KEY,
                            workspace_name VARCHAR(100) NOT NULL,
                            user_id INT REFERENCES users(user_id),
                            is_favorite bool not null default false
);
CREATE TABLE tasks (
                       task_id SERIAL PRIMARY KEY,
                       task_title VARCHAR(100) NOT NULL,
                       description VARCHAR(200) ,
                       start_date timestamp NOT NULL,
                       due_date timestamp NOT NULL,
                       tag VARCHAR(100) NOT NULL,
                       status INT NOT NULL default 1,
                       workspace_id INT REFERENCES workspaces(workspace_id)
);


SELECT
    status,
    COUNT(*) AS task_count
FROM
    tasks
WHERE
        EXTRACT(MONTH FROM start_date) = 4
  AND workspace_id = 18
GROUP BY
    status
ORDER BY
    status;

