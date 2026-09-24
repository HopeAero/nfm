"""Local dev server for the port: http.server with caching turned off.

`python -m http.server` sends Last-Modified and no Cache-Control, so the
browser caches ES modules heuristically and a reload can mix a new module with
a stale one it imports -- "does not provide an export named ..." for code that
is on disk. Run from the repo root:  python web/tools/serve.py [port]
"""
import sys
from http.server import SimpleHTTPRequestHandler, ThreadingHTTPServer


class NoCache(SimpleHTTPRequestHandler):
    def end_headers(self):
        self.send_header('Cache-Control', 'no-store')
        super().end_headers()


port = int(sys.argv[1]) if len(sys.argv) > 1 else 8123
ThreadingHTTPServer(('', port), NoCache).serve_forever()
