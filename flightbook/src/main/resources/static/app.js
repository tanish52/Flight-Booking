// ===== API CONFIG =====
const API = 'http://localhost:8080/api';

// ===== TOKEN HELPERS =====
function getToken()  { return localStorage.getItem('fb_token'); }
function getUser()   { return JSON.parse(localStorage.getItem('fb_user') || 'null'); }
function setToken(t) { localStorage.setItem('fb_token', t); }
function setUser(u)  { localStorage.setItem('fb_user', JSON.stringify(u)); }
function clearAuth() { localStorage.removeItem('fb_token'); localStorage.removeItem('fb_user'); }
function isLoggedIn(){ return !!getToken(); }

// ===== HTTP HELPER =====
async function apiCall(method, endpoint, body = null, auth = true) {
  const headers = { 'Content-Type': 'application/json' };
  if (auth) {
    const token = getToken();
    if (token) headers['Authorization'] = `Bearer ${token}`;
  }
  const opts = { method, headers };
  if (body) opts.body = JSON.stringify(body);
  const res = await fetch(`${API}${endpoint}`, opts);
  const text = await res.text();
  let data;
  try { data = JSON.parse(text); } catch { data = text; }
  if (!res.ok) {
    if (res.status === 403) throw new Error('Access Denied — Admin privileges required for this action.');
    if (res.status === 401) throw new Error('Session expired. Please log in again.');
    throw new Error(typeof data === 'string' ? data : (data?.message || `Error ${res.status}`));
  }
  return data;
}

// ===== NAVBAR STATE =====
function renderNavAuth() {
  const authLinks  = document.getElementById('nav-auth-links');
  const userInfo   = document.getElementById('nav-user-info');
  const navUsername= document.getElementById('nav-username');
  if (!authLinks) return;
  if (isLoggedIn()) {
    authLinks.style.display  = 'none';
    userInfo.style.display   = 'flex';
    const u = getUser();
    if (navUsername && u) navUsername.textContent = `👤 ${u.name || u.email}`;
  } else {
    authLinks.style.display  = 'flex';
    userInfo.style.display   = 'none';
  }
}

function logout() {
  clearAuth();
  window.location.href = '/login.html';
}

// ===== ALERT =====
function showAlert(containerId, msg, type = 'info') {
  const el = document.getElementById(containerId);
  if (!el) return;
  const icons = { success: '✓', error: '✕', info: 'ℹ', warning: '⚠' };
  el.innerHTML = `<div class="alert alert-${type}"><span>${icons[type]}</span> ${msg}</div>`;
  el.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// ===== LOADING =====
function showLoading(msg = 'Loading…') {
  let ov = document.getElementById('loading-overlay');
  if (!ov) {
    ov = document.createElement('div');
    ov.id = 'loading-overlay';
    ov.className = 'loading-overlay';
    ov.innerHTML = `<div class="loading-box"><div class="spinner" style="border-top-color:var(--accent);width:36px;height:36px;border-width:4px;margin:0 auto;"></div><p>${msg}</p></div>`;
    document.body.appendChild(ov);
  }
  ov.querySelector('p').textContent = msg;
  ov.classList.add('show');
}
function hideLoading() {
  const ov = document.getElementById('loading-overlay');
  if (ov) ov.classList.remove('show');
}

// ===== TABS =====
function initTabs() {
  document.querySelectorAll('.tab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      const group = btn.dataset.group || 'default';
      document.querySelectorAll(`.tab-btn[data-group="${group}"]`).forEach(b => b.classList.remove('active'));
      document.querySelectorAll(`.tab-content[data-group="${group}"]`).forEach(c => c.classList.remove('active'));
      btn.classList.add('active');
      const target = document.getElementById(btn.dataset.tab);
      if (target) target.classList.add('active');
    });
  });
}

// ===== REDIRECT GUARD =====
function requireAuth() {
  if (!isLoggedIn()) { window.location.href = '/login.html'; return false; }
  return true;
}

// ===== INIT ON LOAD =====
document.addEventListener('DOMContentLoaded', () => {
  renderNavAuth();
  initTabs();
  const logoutBtn = document.getElementById('nav-logout');
  if (logoutBtn) logoutBtn.addEventListener('click', logout);
});
