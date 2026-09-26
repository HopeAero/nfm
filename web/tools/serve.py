"""Local dev server for the port: http.server with caching turned off.

`python -m http.server` sends Last-Modified and no Cache-Control, so the
browser caches ES modules heuristically and a reload can mix a new module with
a stale one it imports -- "does not provide an export named ..." for code that
is on disk. Run from the repo root:  python web/tools/serve.py [port]

It also marks the pages it serves as a dev server (the `nfm-devserver`
cookie), which turns the URL test switches on without the launcher's
Developer mode setting (web/devmode.js): headless runs start from a fresh
profile. The deployed site sends no such cookie.
"""
import sys
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer


class NoCache(SimpleHTTPRequestHandler):
    def end_headers(self):
        self.send_header('Cache-Control', 'no-store')
        self.send_header('Set-Cookie', 'nfm-devserver=1; Path=/; SameSite=Strict')
        super().end_headers()


port = int(sys.argv[1]) if len(sys.argv) > 1 else 8123
ThreadingHTTPServer(('', port), NoCache).serve_forever()
