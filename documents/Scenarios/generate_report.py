import json
import re
import urllib.request

API_URL = "https://api.github.com/repos/saffathasan/se577-train-system/issues"


def get_open_issues_from_github():
    return get_issues_from_endpoint(API_URL)


def get_closed_issues_from_github():
    return get_issues_from_endpoint(API_URL + "?state=closed")


def get_issues_from_endpoint(endpoint):
    with urllib.request.urlopen(endpoint) as url:
        data = json.loads(url.read().decode())
        return [Issue(data_point) for data_point in data if 'pull' not in data_point['html_url']]


class Issue:
    def __init__(self, github_data):
        self.title = github_data['title']
        self.body = collapse_lines(github_data['body'])

    def format(self):
        title_str = "## {}".format(self.title)
        return "{}\n{}\n".format(title_str, self.body)

    def generate_header(self):
        link = "-".join(self.title.split())
        return "  * [{}](#{})\n".format(self.title, link)


def collapse_lines(string):
    string = ' '.join(string.split(' '))
    return re.sub(r'\r\n', '\n', string)


def save():
    with open("User Stories.md", "w") as f:
        open_issues = get_open_issues_from_github()
        closed_issues = get_closed_issues_from_github()

        open_issues.sort(key=lambda x: x.title)
        closed_issues.sort(key=lambda x: x.title)

        f.write("- [Closed](#Closed-Issues)\n")

        for issue in closed_issues:
            f.write(issue.generate_header())

        f.write("- [Open](#Open-Issues)\n")

        for issue in open_issues:
            f.write(issue.generate_header())

        f.write("# Closed Issues\n")
        for issue in closed_issues:
            f.write(issue.format())

        f.write("# Open Issues\n")
        for issue in open_issues:
            f.write(issue.format())


def main():
    save()


if __name__ == "__main__":
    main()
