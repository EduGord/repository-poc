INSERT INTO public.users (name, username, password) VALUES ('Test User', 'testuser', 'password') ON CONFLICT DO NOTHING;
