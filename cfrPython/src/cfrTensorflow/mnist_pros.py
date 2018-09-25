# load MNIST data
from tensorflow.contrib.learn.python.learn.datasets.mnist import read_data_sets
mnist = read_data_sets("Mnist_data/", one_hot=True)

# start tensorflow interactiveSession
import tensorflow as tf
sess = tf.InteractiveSession()

# weight initialization
def weight_variable(shape):
    initial = tf.truncated_normal(shape=shape, mean=0, stddev=0.1)
    return tf.Variable(initial)

def bias_variable(shape):
    initial = tf.constant(0.1, shape=shape)
    return tf.Variable(initial)

def conv2d(x, w):
    return tf.nn.conv2d(x, w, strides=[1, 1, 1, 1], padding='SAME')
# pooling
def max_pool(x):
    return tf.nn.max_pool(x, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

# Create the model
# placeholder
x = tf.placeholder("float", [None, 784])
y_ = tf.placeholder("float", [None, 10])
# variables
w = tf.Variable(tf.zeros([784,10]))
b = tf.Variable(tf.zeros([10]))

y = tf.nn.softmax(tf.matmul(x,w) + b)

# first: first convolutinal layer
# input
x_image = tf.reshape(x, [-1, 28, 28, 1])
# args
w_conv1 = weight_variable([5, 5, 1, 32])
b_conv1 = bias_variable([32])
# returns
h_conv1 = tf.nn.relu(conv2d(x_image, w_conv1) + b_conv1)

# second: first pool layer
h_pool1 = max_pool(h_conv1)

# third: second convolutional layer
# args
w_conv2 = weight_variable([5, 5, 32, 64])
b_conv2 = bias_variable([64])
#return
h_conv2 = tf.nn.relu(conv2d(h_pool1, w_conv2) + b_conv2)

# forth: second pool layer
h_pool2 = max_pool(h_conv2)
h_pool2_flat = tf.reshape(h_pool2, [-1, 7*7*64])

# fifth: full connected layer
w_fc1 = weight_variable([7*7*64, 1024])
b_fc1 = bias_variable([1024])
h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, w_fc1) + b_fc1)

# dropout
keep_prob = tf.placeholder("float")
h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)

# sixth: softmax layer
w_fc2 = weight_variable([1024, 10])
b_fc2 = bias_variable([10])
y_conv = tf.nn.softmax(tf.matmul(h_fc1_drop, w_fc2) + b_fc2)

# train and evaluate the model
cross_entropy = -tf.reduce_sum(y_*tf.log(y_conv))
train_step = tf.train.AdagradOptimizer(1e-4).minimize(cross_entropy)
correct_prediction = tf.equal(tf.argmax(y_conv, 1), tf.argmax(y_, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))

sess.run(tf.global_variables_initializer())
for i in range(5000):
    batch = mnist.train.next_batch(300)
    if i%100 == 0:
        train_accuracy = accuracy.eval(feed_dict={x:batch[0], y_:batch[1], keep_prob:1.0})
        print("step %d, train accuracy %g" %(i, train_accuracy))
    train_step.run(feed_dict={x:batch[0], y_:batch[1], keep_prob:0.5})

batch = mnist.test.next_batch(5000)
print(type(batch[0]))
print(type(batch[1]))
print(type(mnist.test.images))
print(type(mnist.test.labels))

train_accuracy = accuracy.eval(feed_dict={x:batch[0], y_:batch[1], keep_prob:1.0})
print("11")
print("train accuracy " + str(train_accuracy))